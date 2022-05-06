# Exercise

Implement a hot topic analysis for RSS feeds.

## Specification
Your application should expose two HTTP endpoints:

### API Definition: 

```
/analyse/new
```

### API Input:

This API endpoint should take at least two RSS URLs as a parameter (more are possible) e.g.:

https://news.google.com/news?cf=all&hl=en&pz=1&ned=us&output=rss

### API Response:

For each request executed against the API endpoint you should return an unique identifier, which will be the input for the second API endpoint.

### Workflow:

When the the API is being called, your code should do a HTTP request to fetch the RSS feeds.
Your code should then analyse the entries in this feed and find potential hot topics --> are there any overlaps between the news.

### Example:

RSS Feed one contains following news:
To Democrats, Donald Trump Is No Longer a Laughing Matter
Burundi military sites attacked, 12 insurgents killed
San Bernardino divers return to lake seeking electronic evidence

RSS Feed two contains following news:
Attacks on Military Camps in Burundi Kill Eight
Saudi Women to Vote for First Time
Platini Dealt Further Blow in FIFA Presidency Bid

Your analysis should return that there are news related to Burundi in both feeds.
The analysed data should be stored within a data store and referenced by an unique identifier (see API response).

### API Definition: 

```
/frequency/{id}
```

### API Input:

This API endpoint takes an id as input

### API Output:

Returns the three elements with the most matches, additinally the orignal news header and the link to the whole news text should be displayed.

### Workflow:

When this API is being called, you will read the analysis data stored in the database by using the given id parameter
Return the top three results as a json object ordered by their frequency of occurrence

## Additional Information
You should use following frameworks for your work.

Spring JPA
H2 database running in memory (data will not be persistent across application restarts)
You are free to add / change any libraries which you might need to solve this exercise, the only requirement is that we do not have to setup / install any external software to run this application.

Running the exercise with maven

```mvn spring-boot:run```

### Commiting
You will provide your solution by creating a feature branch using your name (i.e. feature/ivanhorvat) and pushing it to this repository.



# Solution Implementation

## Description of implementation

For implementation, I used java 17, Spring Boot, H2, Rome library for reading RSS feeds and Stanford NLP pipeline for language processing. Project is structured in 3 main packages: api, domain, persistence. Api package contains controllers, requests, responses and dto objects. Domain package represents business layer of application where all business logic for creation is applied. Business logic includes processing, saving and fetching of analyse, rss feed items and topics. Persistence package contains entities, repositories and repository services for creation of unique id for every object. 

## Database

Database consists of 3 objects: Analyse, RssFeedItem and Topic. Analyse contains unique id of hot topic analysis for RSS feeds and lists of topics and RSS feed items from that analyse. Topic contains name of topic and count of that topic occurrence in titles of RSS feed items. RssFeedItem  contains only title and link of RSS feed item because only they are relevant to business logic.
Relationships between Analyse and other two tables are 1:N.

## RssFeedTopicExtractor
RssFeedTopicExtractor is class in charge of extracting topics from RSS feed titles. It uses Stanford Core NLP pipeline which is used to analyse and process text. 
It uses combination of annotators for tokenization, sentence splitting, part of speech recognition, lemmatization, named entity recognition and entity mentions processes which is combination that can process words like "White House" and "North Korea" as single token for topic.

## RssFeedReader
RssFeedReader is class used to read news from RSS feed  URL and process them and creat RssFeedItem objects. It uses Rome library which is specialised for working with RSS feeds.

## Custom Exceptions
There are 3 custom runtime exceptions that code can return: AnalyseNotFoundException, InvalidRequestException, RssFeedReaderException.
AnalyseNotFoundException is thrown when user is trying to get topic with analyse id that doesn't exist.
InvalidRequestException is thrown in Analyse Service when user request doesn't pass validation request process.
RssFeedReaderException is thrown in RssFeedReader class when there is problem with creation of RSS URl, reading with XMLReader or building RSS feed.


## Postman Collection
Postman collection with existing requests can be found in documentation/collections folder inside the project.