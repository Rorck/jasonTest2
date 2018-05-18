// Beliefs

// Plans
+my_pos(X,Y)
   :  rescueGo & not finished
   <- !rescue.
   
+!rescue: true <- move(R).

+rescueGo: true <- 
	move(R);
	-finished.