// Beliefs

// Plans

+my_pos(X,Y)
   :  not bigFinished
   <- !dig.
	
//when the supplier is finished, the digging can begin:
+tunnelPlanned: true <-
	!dig.

+bigTunnelDigged: true <-
	.broadcast(tell,rescueUnitGo).

+!dig : true
   <- !mov1.
   
+!mov1 : not newTunnel
	<- move(R);
		!dig.
	
+!mov1 : newTunnel
	<- !mov2.
	
+mov2: true
	<- !mov3.
	
+!mov3: true
	<- move(R);
		!dig.

@pcf[atomic]
+bigDiggerGo: true <- move_to(Position).