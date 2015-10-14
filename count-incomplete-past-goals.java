<!-- @@Formula:

var countPastGoals = issue.get("customfield_10619").intValue();	//Retrieve the number of past goals.
var countIncompletePastGoals = 0;
var currentStatus = issue.get("status").getName();

// Create array to track past goals.
arrayPastGoals = new String[] {
	"customfield_10106",	// Goal #1
	"customfield_10124",	// Goal #2
	"customfield_10125",	// Goal #3
	"customfield_10126",	// Goal #4
	"customfield_10127"		// Goal #5
};

if ( currentStatus.equals("Self-Review") ) {
	// Calculate Past Goal Self-Reviews
	arrayPastGoalReviews = new String[] {
		"customfield_10107",	// Goal #1 Employee Self-Evaluation
		"customfield_10129",	// Goal #2 Employee Self-Evaluation
		"customfield_10130",	// Goal #3 Employee Self-Evaluation
		"customfield_10131",	// Goal #4 Employee Self-Evaluation
		"customfield_10132"		// Goal #5 Employee Self-Evaluation
	};
} else if ( currentStatus.equals("Reviewer Notes") ) {
	arrayPastGoalReviews = new String[] {
		"customfield_10108",	// Goal #1 Reviewer's Evaluation
		"customfield_10133",	// Goal #2 Reviewer's Evaluation
		"customfield_10134",	// Goal #3 Reviewer's Evaluation
		"customfield_10135",	// Goal #4 Reviewer's Evaluation
		"customfield_10136"		// Goal #5 Reviewer's Evaluation
	};
}

// Calculate how many Past Goal Self Reviews are incomplete.
for ( i=0; i < countPastGoals; i++ ) {
	if ( issue.get( arrayPastGoals[i] ) != null ) {	// If the past goal exists ...
		if ( issue.get(arrayPastGoalReviews[i]) == null ) { // And if the self review is empty ...
			countIncompletePastGoals = countIncompletePastGoals + 1;
		}
	}
}

return countIncompletePastGoals;

-->