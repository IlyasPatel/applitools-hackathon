# Applitools Hackathon

### Getting Started

`git clone ...`

`mvn clean install`

#### Test Location

```src/test/java/com/applitools/hackathon/V1```

```src/test/java/com/applitools/hackathon/V2```


### Applitools Test Results

'Link'

### Versions Used
##### - Java 8 
##### - Chrome 78.0.3904.97



### Personal Thoughts and Improvements

I think Applitools is a great tool and have demonstrated it internally already. I would particulary be interested
in knowing if you can take screenshots within Applications like Outlook, Apple Mail, etc. 

I would normally move the Applitools SDK calls to a steps class so I could potentially add debug setting so 
I can switch off screenshots during development.

Normally I would move URLs into config and pass the Applitools API Key as a parameter but to keep things simple,
I put them as static variables.


### What I could not get working

I could not get the below working, it kept returning empty results. 
```
TestResultsSummary allTestResults = runner.getAllTestResults();
```

```
result summary {
	all results=
		
	passed=0
	unresolved=0
	failed=0
	exceptions=0
	mismatches=0
	missing=0
	matches=0
}
```



Scenario two - would normally break this out into a separate test

Scenario 3 doesn't fully confirm the data is intact