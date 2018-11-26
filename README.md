# TrackSim
Program to build and simulate a car going around a race track

high priority goals
-----

-Make gui fully functional:

  Finish implementing live preview (currently only works with curve, currently only a single element. We want to be able to live preview the next element starting at the end of the previous element)
  
  Add ability to add track elements to the track ("add" button, LinkedList in trackBuilder class, csv file. May also need to convert trackBuilder to a singleton object for this to work)


medium priority goals
-----

  Once the gui is working properly, we want to view the track times in the application. Maybe add another panel displaying track data
  
  Calculate braking distances. This sort of ties into the previous goal but it can and should be implemented separeately and output to the csv file. 



low priority goals
-----
-make a nice gui with resizing and panning around the map 

-read in a CSV file of GPS coordinates and generate a map based on that
