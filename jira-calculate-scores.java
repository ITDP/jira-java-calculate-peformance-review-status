<!-- @@Formula: 
// FYI, this calculated text field is customfield_10600.

var calc_score = 0;	// variable to record total score
var incomplete_ratings = 0;	// variable to track how many ratings still need to be completed before we can calculate the final score
var how_many_ratings = 0;	// How many ratings do we have?

// Create variables that match ratings text, as it appears in the drop-down lists
// We are getting rid of option "6. N/A: Not applicable"
var score_far_exceeds =	"5. Far Exceeded Expectations: Essential functions of position far exceeded";
var score_exceeds =		"4. Exceeds Expectations: Essential functions of position exceeded";
var score_fulfilled =	"3. Fulfilled: Essential functions of position fulfilled";
var score_improve =		"2. Needs Improvement: Essential functions of position mostly fulfilled but needs improvement";
var score_unfulfilled =	"1. Unfulfilled: Essential functions of position not fulfilled";

// Define array that lists other custom fields that contain ratings that we need to consider
String[] score_fields; // create string array that contains the names of the custom fields with ratings
// We don't know in advance how long this array needs to be. The size of the array (number of ratings) might change over time.
score_fields = new String[] {
	"customfield_10309",	// Reviewer Score: Employee Work Quality/Quantity and Dependability
	"customfield_10310",	// Reviewer Score: Employee Analytical Skills and Decision Making
	"customfield_10311",	// Reviewer Score: Employee Initiative and Creativity
	"customfield_10318",	// Reviewer Score: Employee Communication Skills
	"customfield_10313"		// Reviewer Score: Employee Collaboration
};
how_many_ratings = score_fields.length; // Calculate how many ratings we need to count.

// Calculate score 
for (int i=0; i < how_many_ratings; i++) { //Stop when we reach the total # of ratings that we're counting
	if ( issue.get(score_fields[i]) != null ) { // Check whether the rating exists or is null
		if (issue.get(score_fields[i]).equals(score_far_exceeds)) {
			calc_score = calc_score +5;
		} else if (issue.get(score_fields[i]).equals(score_exceeds)) {
			calc_score = calc_score +4;
		} else if (issue.get(score_fields[i]).equals(score_fulfilled)) {
			calc_score = calc_score +3;
		} else if (issue.get(score_fields[i]).equals(score_improve)) {
			calc_score = calc_score +2;
		} else if (issue.get(score_fields[i]).equals(score_unfulfilled)) {
			calc_score = calc_score +1;
		} else {
			// If the rating is none of the above, this is an incomplete rating.
			// Perhaps the score is an old rating like "6. N/A: Not applicable"
			incomplete_ratings = incomplete_ratings +1;
		}
	} else {
		// Then the field is null, such as if there's no rating, and we should increase our count of incomplete ratings.
		incomplete_ratings = incomplete_ratings +1;
	}
}

// Show results
if (incomplete_ratings > 0) {
	return "You're almost there! You have " + incomplete_ratings + " more rating(s) to complete, and then we can calculate the score.";
} else {
	return calc_score;
}

-->