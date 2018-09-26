# ServerClientPi
This program computes pi with the numeric integration method. Pi is the sum of 4/(1+x^2), for many x values. Variable x is rectangular area that belongs in a circle quarter. The more rectangles are inside that quarter the most precise computation will be. In this program we make 100000000 rectangles, but this number can be changed in Server class (numSteps variable).
Every entity (server, client) is a thread. The number of clients is equal to the number of available system’s CPUs. Every client creates other threads to compute a part of the sum that’s described above. Client sums up and sends this computations to server to make the final computation. 
To “force” client threads run simultaneously execute executeClientServer_v2 file from linux terminal. This file needs to be at the same directory with the java files.
Just type: ./executeClientServer_v2
You can run client threads from an IDE too (e.g. eclipse) but in this case the computation will probably be sequential.

