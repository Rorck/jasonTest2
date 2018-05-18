// Agent smallDigger in project jasonTest2

/* Initial beliefs and rules */

/* Initial goals */

catastrophe(10,15).

/* Plans */
+my_pos(X,Y)
   :  not finished
   <- !dig.

//when the catastrophe occurs plan the new tunnel, and note that the new tunnel is planned.
+catastrophe(X,Y): true <-
	planTunnel(X,Y);
	+tunnelPlanned.
	
	
//when the tunnel is planned, the digging can begin:
+tunnelPlanned: true <-
	.broadcast(tell,tunnelPlanned);
	!dig.

+tunnelDigged: true <-
	.broadcast(tell,supplyGo).

+!dig : true
   <- move(R);
      .broadcast(tell,supplyGo).
