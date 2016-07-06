# twittercli4j
Twitter command line tool written in Java.

### Current Commands
- timeline
- search
- trend
- tweet

### Installation

- Create a runnable jar using this repo
- Save it to somewhere that `$PATH` has access to
- Copy `twitter4j-example.properties` to the same folder
 - rename the file `twitter4j.properties` 
 - fill out the correct twitter properties
- Save the `twitter` bash file found in this repo's root directory to the same folder
 - the .jar file mentioned in this file should be the same name as the runnable jar file created earlier
 - set the `twitter` file to have the right permissions (`chmod +x`)

*these instructions are for a linux system with a jvm*

##### Twitter configuration
```
debug=false
oauth.consumerKey=*********************
oauth.consumerSecret=***********************
oauth.accessToken=************************
oauth.accessTokenSecret=***********************
```


### Examples

```
➜  ~ twitter timeline
Pulling your home timeline...
Los Angeles Dodgers (@Dodgers): 
Wall there, don't care. 👌 https://t.co/preNY5fivS
Tue Jul 05 19:35:52 PDT 2016
ID: 750518600574775297, RT: 496, <3: 874
...

➜  ~ twitter trend 
Pulling trends...
#AltonSterling
#NationalKissingDay
#PokemonGO
#Altadena
...

```




### Thanks
- [twitter4j](https://github.com/yusuke/twitter4j) - twitter library
- [rvesse/airline](https://github.com/rvesse/airline) - git-like java structures base off [airlift/airline](https://github.com/airlift/airline)