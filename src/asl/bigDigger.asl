// Beliefs

// Plans

+my_pos(X,Y)
   : supplyFinished & not bigFinished
   <- !dig.
	
//when the supplier is finished, the digging can begin:
+supplyFinished: true <-
	move(R).

+bigTunnelDigged: true <-
	.broadcast(tell,rescueGo).

+!dig : true
   <- move(R).

@pcf[atomic]
+bigDiggerGo: true <- move_to(Position).