#!/usr/bin/env python3

from requests import post, get

# Steps implemented in this test.
#
# 1 Prepare the Sandbox to run the test.
#  -> (a) Make a EBICS host, (b) make a EBICS subscriber
#     for the test runner, and (c) assign a IBAN to such
#     subscriber.
#
# 2 Prepare the Nexus to run the test.
#  -> (a) Make a Nexus user, (b) make a EBICS subscriber
#     associated to that user, and (c) fetch the bank
#     account owned by that subscriber at the bank.
#
# 3 Upload keys from Nexus to the Bank (INI & HIA)
# 4 Download key from the Bank (HPB) to the Nexus
#
# 5 Request history from the Nexus to the Bank (C53).
# 6 Verify that history is empty.
# 7 Issue a payment from Nexus (Prepare & trigger CCT)
# 8 Request history again, from Nexus to Bank.
# 9 Verify that previous payment shows up.


# Nexus user details
USERNAME="person"

# EBICS details
EBICS_URL="http://localhost:5000/ebicsweb"
HOST_ID="HOST01"
PARTNER_ID="PARTNER1"
USER_ID="USER1"
EBICS_VERSION = "H004"

# Subscriber's bank account
SUBSCRIBER_IBAN="GB33BUKB20201555555555"
SUBSCRIBER_BIC="BUKBGB22"
SUBSCRIBER_NAME="Oliver Smith"
BANK_ACCOUNT_LABEL="savings"

#0.a
resp = post(
    "http://localhost:5000/admin/ebics-host",
    json=dict(
	hostID=HOST_ID,
	ebicsVersion=EBICS_VERSION
    )
)

assert(resp.status_code == 200)

#0.b
resp = post(
    "http://localhost:5000/admin/ebics-subscriber",
    json=dict(
        hostID=HOST_ID,
	partnerID=PARTNER_ID,
	userID=USER_ID
    )
)

assert(resp.status_code == 200)

#0.c, WIP
resp = post(
    "http://localhost:5000/admin/ebics-subscriber/bank-account",
    json=dict(
        subscriber=dict(
            hostID=HOST_ID,
            partnerID=PARTNER_ID,
            userID=USER_ID
	),
        iban=SUBSCRIBER_IBAN,
        bic=SUBSCRIBER_BIC,
        name=SUBSCRIBER_NAME
	label=BANK_ACCOUNT_LABEL
    )
)

assert(resp.status_code == 200)

#1 Create a Nexus user

resp = post(
    "http://localhost:5001/users/{}".format(USERNAME),
    json=dict(
	password="secret"
    )
)

assert(resp.status_code == 200)

#2 Create a EBICS user
resp = post(
    "http://localhost:5001/ebics/subscribers/{}".format(USERNAME),
    json=dict(
	ebicsURL=EBICS_URL,
	hostID=HOST_ID,
	partnerID=PARTNER_ID,
	userID=USER_ID
    )
)

assert(resp.status_code == 200)

#3 Upload keys to the bank INI & HIA
resp = post(
    "http://localhost:5001/ebics/subscribers/{}/sendINI".format(USERNAME),
    json=dict()
)

assert(resp.status_code == 200)

resp = post(
    "http://localhost:5001/ebics/subscribers/{}/sendHIA".format(USERNAME),
    json=dict()
)

assert(resp.status_code == 200)

#4 Download keys from the bank HPB
resp = post(
    "http://localhost:5001/ebics/subscribers/{}/sync".format(USERNAME),
    json=dict()
)

assert(resp.status_code == 200)

#5 Request history via EBICS
resp = post(
    "http://localhost:5001/ebics/subscribers/{}/collect-transactions-c53".format(USERNAME),
    json=dict()
)

assert(resp.status_code == 200)

resp = get(
    "http://localhost:5001/users/{}/history".format(USERNAME)
)

assert(
    resp.status_code == 200 and \
    len(resp.json().get("payments")) == 0
)

#6 Prepare a payment (via pure Nexus service)

# FIXME: this currently fails, because nexus has a
# empty table w.r.t. bank accounts.  Thus, the sandbox
# must provide such information via the usual HTD message.

resp = post(
    "http://localhost:5001/users/{}/prepare-payment".format(USERNAME),
    json=dict(
        creditorIban="GB33BUKB20201555555555",
        creditorBic="BUKBGB22",
        creditorName="Oliver Smith",
        debitorIban="FR7630006000011234567890189",
        debitorBic="AGRIFRPP",
        debitorName="Jacques LaFayette"
    )
)

assert(resp.status_code == 200)

#7 Execute such payment via EBICS
#8 Request history again via EBICS