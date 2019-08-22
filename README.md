# origo assignment
 
A simple program to print information about [Oslos Bysykkler](https://oslobysykkel.no/apne-data/sanntid) (city bicycles) given as an assignment from Oslo Origo (see assignment.txt).
 
## Usage (Windows)
1. Download dependencies with Maven
2. Run Main under [src/main/java](src/main/java)

## My Thoughts

Since it's been a while since I've used Java it went a little slower than expected but it turned out pretty good, even picked up some neat formatting tricks on the way.
I'm guessing part of the challenge was combining the two APIs but the assignment doesn't strictly say that you need names, addresses etc. but I figured it's part of making the program actually usable and went ahead anyway.
I tried to make some methods reusable, but in the end that might have complicated things more than necessary, especially for this assignment.

PS: Some glitches occur in the formatedPrint, due to inconsistencies in the API, such as a space or newline after ID 406 "Carl Berners plass " or ID 550 "Therese's gate 49 ", which seems to be an issue on the API.
