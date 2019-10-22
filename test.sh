file1 = stuff1.txt
file2 = stuff2.c
file3 = stuff3.java
for file in file1 file2 file3
  do
    if file then
       cp file bck
    else
      cp file asdf
    fi
  od