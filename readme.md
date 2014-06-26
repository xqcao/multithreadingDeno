
big data (>1G),split to small subfiles(less 1G), use multithreading,each thread get one hashMap,then merge all hashMap into one hashmap,then sort

1. split file
2. multithreading with each subfile
3. get return hashmap
4. merge all hashmap
5. sort hashmap
6. write file 
