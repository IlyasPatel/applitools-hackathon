# Applitools Hackathon

### Getting Started

`git clone https://github.com/IlyasPatel/applitools-hackathon`

`mvn clean install`

#### Test Location

```src/test/java/com/applitools/hackathon/V1```

```src/test/java/com/applitools/hackathon/V2```


### Applitools Test Results

[Applitools Test Results](https://eyes.applitools.com/app/test-results/00000251827257996512/?accountId=95HefRuux0G_ppJdVwdZAg~~)
[Base Results](https://eyes.applitools.com/app/test-results/00000251827258195401/?accountId=95HefRuux0G_ppJdVwdZAg~~)
### Versions Used
##### - Java 8 
##### - Chrome 78.0.3904.97



### Personal Thoughts and Improvements

I think Applitools is a great tool and have demonstrated it internally already. I would particulary be interested
in knowing if you can take screenshots within Applications like Outlook, Apple Mail, etc. 

I would normally move the Applitools SDK calls to a steps class so I could potentially add debug setting so 
I can switch off screenshots during development.

Also, I would move URLs into config and pass the Applitools API Key as a parameter but to keep things simple,
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

I found the batch recommendation confusing in scenario two. I used a batch for scenario two and four but the final
test results also need to go into a batch as requested. I couldn't find a way of batching the two test scenarios 
with the final results.