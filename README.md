### Socket API/FLOW

- POST: Session -> RES: Status(OK / WAIT)
- GET: Status(Session) -> RES: STATUS(OK) + Session / Status(WAIT)
- GET: Status(Turn) -> RES: Status(OK / WAIT)
- POST: Missile -> RES: Status(OK / WAIT)
- GET: Status(Missile) -> RES: Status(OK) + Missile / Status(WAIT)
PS: Status(NO) means bad/malformed request