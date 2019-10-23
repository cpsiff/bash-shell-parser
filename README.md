# Bash Shell Parser

## Usage

Command Line Parameters:<br>
`full_file_path` (required) path of input file to parse<br>
`-p` (optional) output to file <br>
`-d` (optional) output to system.out

Example:<br>
`compile2C -p -d /home/carter/IdeaProjects/bash-sub-shell/test.sh`

## Test Cases
Some test cases that I have confirmed to work:

```$xslt
file1 = stuff1.txt
file2 = stuff2.c
file3 = stuff3.java
for file in file1 file2 file3
  do
    if test -e file then
       cp file bck
    else
    fi
  od
```

```$xslt
file1 = stuff1.txt
file2 = stuff2.c
file3 = stuff3.java
file4 = asdfasdf
file5 = qwerqwer
```