# ITDP Jira Java Calculated Scores
ITDP Performance Review system, score calculations

## How to use these files

0. You'll need admin rights within ITDP's Jira instance.
1. Within Jira, visit the Custom Fields section.
2. Find the first custom field listed below, and edit it. You'll see that the custom field contains two fields: "Field Name" and "Description".
3. Copy and paste the code from the corresponding Java file into the "Description" field, and save your changes:
	* count-incomplete-past-goals.java = Count of Incomplete Past Goals
	* count-past-goals.java = Count of Past Goals
	* status-key-chars.java = Status: Key Characteristics
	* status-past-and-future-goals.java = Status: Future Goals
	* status-past-and-future-goals.java = Status: Past Goals
4. Repeat step 3 for each of the custom fields listed above.
5. Note that the last two custom fields use the same code. Although we try to follow the "DRY" principle -- "Don't Repeat Yourself" -- when coding, we need to copy and paste this code into two places due to a Jira limitation.
6. Examine a performance review in order to see your changes.

And that's it.

Reach out if you have any questions, difficulties, or ideas to improve this repository.

Thank you.

-- Joe