<!-- @@Formula:

var colorITDPOrange = "#F66F42";
var colorITDPDarkGray = "#6f6e73";

var textStatus;	// We'll use this to output to the screen
var currentStatus = issue.get("status").getName();

// Style the "Done" and "Next" buttons, e.g. "Send to Reviewer" and "Share With Employee", when they appear.
textStatus = "<style>"
+ "#action_id_731, #action_id_741, #action_id_801, #action_id_821, #action_id_831, #action_id_841 { background-color:" + colorITDPOrange + "; color: #fff; text-shadow: 0px 1px 0px " + colorITDPOrange + "; }"
+ "#action_id_731:hover, #action_id_741:hover, #action_id_801:hover, #action_id_821:hover, #action_id_831:hover, #action_id_841:hover {background-color: " + colorITDPDarkGray + "; text-shadow: 0px 1px 0px " + colorITDPDarkGray + "; }"
// Also style the statusUpdate box.
+ ".statusUpdate { background-color:#f0f0f0; border: 1px solid #ddd; border-radius: 3px; padding: 20px; }"
+ ".statusUpdate ul { color: " + colorITDPOrange + "; margin-top: 20px; }"
+ "</style>";

// Period of Review must be 2015, or else do nothing else.
var periodOfReview = issue.get("customfield_10112");	// Period of Review custom field
if ( periodOfReview.equals("2015") ) {
	
	// Create variables that we'll use to calculate how much work is left.
	var countIncompleteKeyChars = 0;
	var countIncompletePastGoals = 0;
	var countIncompleteFutureGoals = 0;
	var incompleteOverallReviewScore = 0;
	var countIncompleteTotal = 0;
	
	var countKeyCharReviews = 0;
	var countPastGoals = 0;
	var countFutureGoalReviews = 0;
	
	if ( currentStatus.equals("Self-Review") ||  currentStatus.equals("Reviewer Notes") ) {
		
		// Retrieve Past Goals Status
		// Load Count of Incomplete Past Goals, and convert to integer.
		// By default, it converts to a decimal when we import it.
		countIncompletePastGoals = issue.get("customfield_10618").intValue();
		
		// Calculate Future Goals Status
		arrayFutureGoalReviews = new String[] {
			"customfield_10113",	// New Goal #1
			"customfield_10137",	// New Goal #2
			"customfield_10138",	// New Goal #3
			"customfield_10139",	// New Goal #4
			"customfield_10140",	// New Goal #5
			"customfield_10115",	// New Professional Development Goals
		};
		countFutureGoalReviews = arrayFutureGoalReviews.length; // Calculate how many Future Goal Self Reviews we need to count.
		
		// Calculate how many Future Goal Self Reviews are incomplete.
		for ( i=0; i < countFutureGoalReviews; i++ ) {
			if ( issue.get(arrayFutureGoalReviews[i]) == null ) { // Check whether the rating exists or is null
				countIncompleteFutureGoals = countIncompleteFutureGoals + 1;
			}
		}
		
		// Calculate Incomplete Key Chars if we're in the Self-Review stage.
		if ( currentStatus.equals("Self-Review") ) {
			
			// Calculate Key Chars Status
			arrayKeyCharReviews = new String[] {
				"customfield_10601",	// Employee Self-Evaluation: Success in Fulfilling Established Goals
				"customfield_10606",	// Employee Self-Evaluation: Teamwork and Collaboration
				"customfield_10607"		// Employee Self-Evaluation: Productivity
			};
			countKeyCharReviews = arrayKeyCharReviews.length; // Calculate how many Key Char Self Reviews we need to count.
			
			// Calculate how many Key Char Self Reviews are incomplete.
			for ( i=0; i < countKeyCharReviews; i++ ) {
				if ( issue.get(arrayKeyCharReviews[i]) == null ) { // Check whether the rating exists or is null
					countIncompleteKeyChars = countIncompleteKeyChars + 1;
				}
			}
		
		// Calculate Incomplete Key Chars if we're in the Reviewer Notes stage.
		} else if ( currentStatus.equals("Reviewer Notes") ) {
			
			// Calculate Key Chars Status
			arrayKeyCharReviews = new String[] {
				"customfield_10602",	// Reviewer Evaluation: Success in Fulfilling Established Goals
				"customfield_10611",	// Reviewer Evaluation: Teamwork and Collaboration
				"customfield_10612"		// Reviewer Evaluation: Productivity
			};
			countKeyCharReviews = arrayKeyCharReviews.length; // Calculate how many Key Char Self Reviews we need to count.
			
			// Calculate how many Key Char Reviews are incomplete.
			for ( i=0; i < countKeyCharReviews; i++ ) {
				if ( issue.get(arrayKeyCharReviews[i]) == null ) { // Check whether the rating exists or is null
					countIncompleteKeyChars = countIncompleteKeyChars + 1;
				}
			}
			
			/* Is the Overall Review Score Empty?
				Let's track this separately from the other Key Char reviews
				because it might confuse the status update.
				For example, if a reviewer has four (4) incomplete reviews,
				they might be confused because they are only expecting to have three Key Char (3) reviews to complete.
			*/
			if ( issue.get("customfield_10613") == null ) { // Overall Review Score
				incompleteOverallReviewScore = 1;
			}
			
		}
			
		countIncompleteTotal = countIncompleteKeyChars + countIncompletePastGoals + countIncompleteFutureGoals + incompleteOverallReviewScore;
		// Calculate Total Number of Empty Review Fields
		
		textStatus = textStatus
		+ "<div class=\"statusUpdate\">"; // Create box for text Status Update
		
		if ( countIncompleteTotal > 0 ) {
			
			textStatus = textStatus
			+ "You have a few more fields to complete, and then you'll be done:<br />"
			+ "<ul>";
			
			if (countIncompletePastGoals>0) {
				textStatus = textStatus + "<li><b>" + countIncompletePastGoals + "</b> more Past Goals</li>";
			}
			
			if (countIncompleteKeyChars>0) {
				textStatus = textStatus + "<li><b>" + countIncompleteKeyChars + "</b> more Key Characteristics</li>";
			}
			
			if (incompleteOverallReviewScore>0) {
				textStatus = textStatus + "<li><b>" + incompleteOverallReviewScore + "</b> more Key Characteristics Overall Score</li>";
			}
						
			if (countIncompleteFutureGoals>0) {
				textStatus = textStatus + "<li><b>" + countIncompleteFutureGoals + "</b> more Future Goals</li>";
			}
			
			textStatus = textStatus
			+ "</ul>"
			+ "<br />After you complete the missing information, you'll see the \"Done\" button appear.<br />";
			
		} else {
			
			textStatus = textStatus
			+ "<p>Congratulations! Your self-review looks good to this computer system.</p>"
			+ "<p><b>Please click the button labeled \"Done: Send to Reviewer\" now</b>.</p>"
			+ "<p>We'll email you a link to this review form again soon, after your reviewer completes their feedback.</p>";
			
		}
		
		textStatus = textStatus
		+ "</div>";
	}
}

return textStatus;

-->