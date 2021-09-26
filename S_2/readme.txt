/**********************************************************************
 *  Pattern Recognition readme.txt template
 **********************************************************************/

Name:             
Login:            
Group:

Partner name:     Pétur Steinn Guðmundsson
Partner login:    petursg20
Partner group:    1

Username of the student submitting to Mooshak: petursg20

Hours to complete assignment (optional): Too many.



/**********************************************************************
 *  Step 1.  Explain *briefly* how you implemented brute force.
 *           Describe how you implemented compareTo() and the
 *           slopeTo() methods in the Point data type.
 **********************************************************************/

First of all before we pass the points into the Brute class, we start by sorting our points using the comparator we implemented in Point.java.
When the Brute class is initiated we create a big_list_boi ArrayList which holds subarrays of points that are collinear.
Next we do a quadratic for loop which iterates over all the sorted points in the array given in the constructor.
In the most-inner for loop we do the following checks:
check_order(point_a, point_b, point_c, point_d)
The points given to this method are taken from the outer for loops.
What check_order does is that it checks if these points are worth looking at, it simply does the lexicographic check of a,b,c,d.
Point a needs to be beneith or left to point b, point b needs to be beneith or left to point c, and point c needs to be beneith or left to point d.
If this is true then we make sure to check their slopes with the check_slope() method.
If that method returns true, meaning the slope of all the 4 points match we do the following:
Create an array with those four points and add them to the big_list_boi array.
When we have exhausted the points in the for loops. We go ahead and print out the big_list_boi which has the results.



/**********************************************************************
 *  Step 2.  Explain *briefly* how you implemented the sorting solution.
 *           What steps did you do to avoid printing permutations
 *           and subsegments?
 **********************************************************************/

Same as before, we sort the array of points before passing them into the Fast constructor.
Now we have a double nested for loop. The first one iterates the "origin points" and the inner for loop iterates the "non-origin points"d.
In the first for loop we do the following:
Initialize a slope that has NaN as value.
Next we create an array of all the remaining points and sort them according to their slope to the origin point at hand.
We create another array called inner_array which will hold the points that have the same slope as p (the origin), this logic will be implemented later in the code.
Next we hit the inner for loop which iterates over all the sorted points.
The logic behind our implementation on how we do all the logic of checking wether there are 3 or more points that are collinear to p is explained in detail within the Fast.java.
Please look at the code for further explanation.
It is rather hard to explain those parts "briefly".
The most brief explanation of the logic is that we check if we have three or more consecutive points with the same slope as p and add them to an array and print it out.


/**********************************************************************
 *  Empirical    Fill in the table below with actual running times in
 *  Analysis     seconds when reasonable (say 180 seconds or less).
 *               You can round to the nearest tenth of a second.
 *
 *  Estimate (using tilde notation) the running time (in seconds) of
 *  your two main functions as a function of the number of points N.
 *
 *  Explain how you derive any exponents.
 **********************************************************************/

    
      N       brute       sorting
 ---------------------------------
    150
    200
    300
    400
    800
   1600
   3200
   6400
  12800


Brute:    ~

Sorting:  ~




/**********************************************************************
 *  Theoretical   Give the order of growth of the worst-case running
 *                time of your programs as a function of N. Justify
 *                your answer briefly.
 **********************************************************************/

Brute:

Sorting:



/**********************************************************************
 *  Known bugs / limitations. For example, if your program prints
 *  out different representations of the same line segment when there
 *  are 5 or more points on a line segment, indicate that here.
 **********************************************************************/



/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/



/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/




/**********************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 **********************************************************************/







/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
