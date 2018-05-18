// Beliefs

// Plans

+my_pos(X,Y)
   :  not bigFinished
   <- !dig.
	
//when the supplier is finished, the digging can begin:
+supplyFinished: true <-
	!dig.

+bigTunnelDigged: true <-
	.broadcast(tell,rescueUnitGo).

+!dig : true
   <- move(R).

@pcf[atomic]
+bigDiggerGo: true <- move_to(Position).