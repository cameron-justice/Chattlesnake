# Branch Style Guide and Explanation
## For the Chattlesnake Project
---
##Explanation of Branches

###Why branches?
    Branches in git allow developers to modify sections of the code outside of the master branch. This means that one user can push their changes, and another user can pull those changes, without the master branch ever changing.
    This means that, when deployed, the application can have a stable source of code to draw from and be installed by users or developers, but developers can still modify the code.

An official explanation of branches and how they work: [Git on Branches](https://git-scm.com/book/en/v1/Git-Branching-What-a-Branch-is)

###Creating a New Branch
To create a new branch, simply enter into your terminal:
`git branch [branch-name]`

###Switching branches
To switch to an already created branch, simply enter into your terminal:
`git checkout [branch-name]`

###What changes?
When working on a branch, your "Head" is pointed to the branch you're working in. This means that your commits change the branch and not Master or any other branches.

## Branch Naming Guide
Branch names should always be:
- Short but descriptive
- lowercase
- words seperated by hyphen

Branch names should also be prefixed by their purpose. Purposes in this project will be:
- feature
- bugfix
- featureupdate
- featurereplace
(The above list may be extended in future)

### Example Branch Names
For a branch that will be implementing a new feature of a local log:
`feature-local-log`
To create this branch:
`git branch feature-local-log`
To checkout (switch to) this branch:
`git checkout feature-local-log`

For a branch that will be replacing the current stylesheet design:
`featurereplace-stylesheet-design`
To create this branch:
`git branch featurereplace-stylesheet-design`
To checkout (switch to) this branch:
`git checkout featurereplace-stylesheet-design`

For a branch that will be fixing a bug, ensure the bug is listed as a repository issue, example "issue-15":
`bugfix-issue-15`
To create this branch:
`git branch bugfix-issue-15`
To checkout (switch to) this branch:
`git checkout bugfix-issue-15`

### Multiple Branches For One Item
In an instance where >1 developer will be working on an item, the branch name will follow the same pattern, but will be appended by the developer's first name and last initial.

Example:
`git branch bugfix-issue-15\chrisG`
`git branch bugfix-issue-15\nathanB`

## Final Comments
Branches allow development to flow smoothly while allowing developers to work across the entire project.

Aside from extremely simple changes (such as updating this file), **all changes and work should be done in a non-master branch**.

However, your branches do not need to be extremely specific. For example, a branch for `feature-networking` is barely descriptive, but gives other developers the idea of what the branch is for.
