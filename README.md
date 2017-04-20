# twittercli4j
Twitter command line tool written in Java.

### Current Commands
```
➜  ~ twitter help
usage: twitter <command> [ <args> ]

Commands are:
    dm         DirectMessages
    favorite   Favorite a tweet (tweetId)
    friends    Get your friends list
    help       Display help information
    location   get twitter location information
    search     Search twitter
    timeline   Get your timeline
    trends     Get what's trending
    tweet      Tweet your thoughts

See 'twitter help <command>' for more information on a specific command.
```

The default command is `timeline`

### Installation

- Create a runnable jar (mvn package)
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

##### Alternative Twitter Configuration
If using `twitter4j.properties` doesn't work (maybe using the maven build), configurations can be added as flags to the `java` command line.

```
#!/bin/bash
# twitter

java \
	-Dtwitter4j.debug=false \
	-Dtwitter4j.oauth.consumerKey=********************* \
	-Dtwitter4j.oauth.consumerSecret=********************* \
	-Dtwitter4j.oauth.accessToken=********************* \
	-Dtwitter4j.oauth.accessTokenSecret=********************* \
    -jar (path to command)/twittercli.jar $*

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

➜  ~ twitter trends
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