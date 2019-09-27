package tech.libeufin.tech.libeufin

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

const val CUSTOMER_NAME_MAX_LENGTH = 20
const val SUBSCRIBER_ID_MAX_LENGTH = 10
const val PUBLIC_KEY_MAX_LENGTH = 256 // FIXME review this value!
const val PRIV_KEY_MAX_LENGTH = 512 // FIXME review this value!
const val SQL_ENUM_SUBSCRIBER_STATES = "ENUM('NEW', 'PARTIALLI_INITIALIZED_INI', 'PARTIALLY_INITIALIZED_HIA', 'INITIALIZED', 'READY')"

/**
 * All the states to give a subscriber.
 */
enum class SubscriberStates {
    /**
     * No keys at all given to the bank.
     */
    NEW,

    /**
     * Only INI electronic message was succesfully sent.
     */
    PARTIALLY_INITIALIZED_INI,

    /**
     * Only HIA electronic message was succesfully sent.
     */
    PARTIALLY_INITIALIZED_HIA,

    /**
     * Both INI and HIA were electronically sent with success.
     */
    INITIALIZED,

    /**
     * All the keys accounted in INI and HIA have been confirmed
     * via physical mail.
     */
    READY
}

/**
 * All the states that one key can be assigned.
 */
enum class KeyStates {

    /**
     * The key was never communicated.
     */
    MISSING,

    /**
     * The key has been electronically sent.
     */
    NEW,

    /**
     * The key has been confirmed (either via physical mail
     * or electronically -- e.g. with certificates)
     */
    RELEASED
}

/**
 * This table information *not* related to EBICS, for all
 * its customers.
 */
object BankCustomers: IntIdTable() {
    // Customer ID is the default 'id' field provided by the constructor.
    val name = varchar("name", CUSTOMER_NAME_MAX_LENGTH)
    val ebicsSubscriber = reference("ebicsSubscriber", EbicsUsers)
}

class BankCustomer(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BankCustomer>(BankCustomers)

    var name by BankCustomers.name
    var ebicsSubscriber by EbicsSubscriber referencedOn BankCustomers.ebicsSubscriber
}

/**
 * The following tables define IDs that make a EBCIS
 * 'subscriber' exist.  Each EBICS subscriber is the tuple:
 *
 * - UserID, the human who is performing a EBICS task.
 * - PartnerID, the legal entity that signed a formal agreement with the financial institution.
 * - SystemID, (optional) the machine that is handling the EBICS task on behalf of the UserID.
 */

/**
 * Table for UserID.
 */
object EbicsUsers: IntIdTable() {
    // For simplicity, this entity is implemented by the
    // 'id' field provided by the table constructor by default.
}

class EbicsUser(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EbicsUser>(EbicsUsers)
}

/**
 * Table for UserID.
 */
object EbicsPartners: IntIdTable() {
    // For simplicity, this entity is implemented by the
    // 'id' field provided by the table constructor by default.
}


class EbicsPartner(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EbicsPartner>(EbicsPartners)
}


/**
 * Table for UserID.
 */
object EbicsSystems: IntIdTable() {
    // For simplicity, this entity is implemented by the
    // 'id' field provided by the table constructor by default.
}

class EbicsSystem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EbicsSystem>(EbicsSystems)
}

/**
 * Subscribers table.  This table associates users with partners
 * and systems.  Each value can appear multiple times in the same column.
 */
object EbicsSubscribers: IntIdTable() {
    val userId = reference("UserId", EbicsUsers)
    val partnerId = reference("PartnerId", EbicsPartners)
    val systemId = reference("SystemId", EbicsSystems)

    val signatureKey = reference("signatureKey", EbicsPublicKey).nullable()
    val encryptionKey = reference("encryptionKey", EbicsPublicKey).nullable()
    val authorizationKey = reference("authorizationKey", EbicsPublicKey).nullable()

    val state = customEnumeration(
        "state",
        SQL_ENUM_SUBSCRIBER_STATES,
        {SubscriberStates.values()[it as Int]},
        {it.name})
}

class EbicsSubscriber(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<EbicsSubscriber>(EbicsSubscribers)

    var userId by EbicsUser referencedOn EbicsSubscribers.userId
    var partnerId by EbicsPartner referencedOn EbicsSubscribers.partnerId
    var systemId by EbicsSystem referencedOn EbicsSubscribers.systemId

    var signatureKey by EbicsPublicKey.id
    var encryptionKey by EbicsPublicKey.id
    var authorizationKey by EbicsPublicKey.id
    var state by EbicsSubscribers.state
}

/**
 * Helper function that makes a new subscriber
 * @return new object
 */
fun createSubscriber() : EbicsSubscriber {

    return EbicsSubscriber.new {
        userId = EbicsUser.new {  }
        partnerId = EbicsPartner.new { }
        systemId = EbicsSystem.new {  }
        state = SubscriberStates.NEW
    }
}


/**
 * This table stores RSA public keys.
 */
object EbicsPublicKey: IntIdTable() {
    val pub = binary("pub", PUBLIC_KEY_MAX_LENGTH)
    val state = customEnumeration(
        "state",
        "ENUM('MISSING', 'NEW', 'RELEASED')",
        {KeyStates.values()[it as Int]},
        {it.name})
}

/**
 * This table stores RSA private keys.
 */
object EbicsPrivateKEy: IntIdTable() {
    val pub = binary("priv", PRIV_KEY_MAX_LENGTH)
}

fun dbCreateTables() {
    Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(
            BankCustomers,
            EbicsUsers,
            EbicsPartners,
            EbicsSystems,
            EbicsSubscribers
        )
    }
}