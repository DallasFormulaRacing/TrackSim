# TrackSim
Program to build and simulate a car going around a race track

high priority goals
-----

  Finish implementing live preview (currently only works with curve, currently only a single element. We want to be able to live preview the next element starting at the end of the previous element)
  
  Add ability to add track elements to the track ("add" button, LinkedList in trackBuilder class, csv file. May also need to convert trackBuilder to a singleton object for this to work)


medium priority goals
-----

  Once the gui is working properly, we want to view the track times in the application. Maybe add another panel displaying track data
  
  Calculate braking distances. This sort of ties into the previous goal but it can and should be implemented separeately and output to the csv file. 
  
  Polish up the gui. We have working sliders and buttons but the formatting is broken (???). The straight/curve toggle buttons don't do anything but this is dependent on some of the other previous goals. Add button doesnt do anything.



low priority goals
-----
-make a nice gui with resizing and panning around the map 

-read in a CSV file of GPS coordinates and generate a map based on that
