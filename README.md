### Socket API/FLOW

- SEND: Session -> RES: Status(OK / NO)
- REQ: Status(Session) -> RES: Session / Status(WAIT)
- REQ: Status(Turn) -> RES: Status(OK / NO / WAIT)
- REQ: Missile -> RES: Status(OK / NO / WAIT)
- REQ: Status(Missile) -> RES: Status(OK) + Missile / Status(NO/WAIT)