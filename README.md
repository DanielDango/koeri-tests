# kœri tests

A collection of JUnit 5 tests for the two hypothetical classes `IP` and `Network`.

## Installation
If you too share a great interest in forests of IPv4 addresses, you may use these tests by creating the required
classes, namely `src/main/java/edu/kit/informatik/(IP|Network|ParseException).java`.

### Linux and MacOS
Here's a little copy+paste command:

```bash
git clone https://github.com/koerihub/koeri-tests.git
# or if you use ssh:
#   git clone git@github.com:koerihub/koeri-tests.git
mkdir -p koeri-tests/src/main/java/edu/kit/informatik
```

Now you can copy/move the three files (`IP.java`, `Network.java`, `ParseException.java`) to
the new-created `informatik` directory and adjust the imports.

### Windows
Create the directories in the file-explorer.

## Contribute
Please contribute your own test cases by adding a new test class or adjusting an existing one and submitting the changes
as a merge request.
