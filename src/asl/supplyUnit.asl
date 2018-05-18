// Beliefs

// Plans
+my_pos(X,Y)
   :  supplyGo & not finished
   <- !deliver.

+finished: true
	<- .broadcast(tell,supplyFinish).   
   
+!deliver: true <- move(R).
+supplyGo: true <- 
	move(R);
	-finished.