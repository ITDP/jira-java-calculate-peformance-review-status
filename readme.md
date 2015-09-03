# ITDP Jira Java Calculated Scores
ITDP Performance Review system, score calculations

## How to use these files

0. You'll need admin rights within ITDP's Jira instance.
1. Within ITDP's Jira instance, edit custom field #10600, the calculated scores custom field.
2. Copy the code in this repository to the custom field's "Description" field, then save your changes.
3. Examine a performance review in order to see your changes. Note: The calculated scores field will simply not appear if your code introduces a Null Pointer Exception or other error. You may need to roll back changes or request access to the server logs.

And that's it.

Reach out if you have any questions, difficulties, or ideas to improve this repository.

Thank you.

-- Joe