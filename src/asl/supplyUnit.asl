// Beliefs

// Plans
+my_pos(X,Y)
   :  supplyGo & not finished
   <- !deliver.

+finished: true
	<- .broadcast(tell,supplyFinished).   
   
+!deliver: true <- move(R).
+supplyGo: true <- 
	move(R);
	-finished.