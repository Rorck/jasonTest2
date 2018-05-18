// Beliefs

// Plans

+my_pos(X,Y)
   : supplyFinished & not bigFinished
   <- !dig.
	
//when the supplier is finished, the digging can begin:
+supplyFinished: true <-
	!dig.

+bigTunnelDigged: true <-
	.broadcast(tell,rescueGo).

+iShouldGo: true <- move(R).

+iShouldntGo: true <- 
	.broadcast(tell,supplyGo).

+!dig : true
   <- move(R).

+!decide: true
<- decide(R);
-supplyFinished.

@pcf[atomic]
+bigDiggerGo: true <- move_to(Position).