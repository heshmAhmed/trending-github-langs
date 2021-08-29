# trending-github-languages
 
 > Rest Microservice to Get trending repos and trending programming languages used, in the last 30 days on Github

### Example

 Request: `/api/v1/trending-repos`
 
 Response:
 ```
[
  {
    "count": 100,
    "items": [
      {
        "id": 399495186,
        "name": "sqlmodel",
        "html_url": "https://github.com/tiangolo/sqlmodel",
        "languages_url": "https://api.github.com/repos/tiangolo/sqlmodel/languages",
        "language": "Python"
      },
      {
        "id": 393571599,
        "name": "MockingBird",
        "html_url": "https://github.com/babysor/MockingBird",
        "languages_url": "https://api.github.com/repos/babysor/MockingBird/languages",
        "language": "Python"
      },...
```

If you want programing languages used in trending repos send request to `/api/v1/trending-repos/languages`
 
 Response:
 ```
{
  "Objective-C": {
  "count": 1,
   "items": [
     {
       "id": 392163160,
       "name": "Fastbot_iOS",
       "html_url": "https://github.com/bytedance/Fastbot_iOS",
       "languages_url": "https://api.github.com/repos/bytedance/Fastbot_iOS/languages",
       "language": "Objective-C"
     }
   ]
 },
 "Swift": {
   "count": 2,
   "items": [
     {
       "id": 397679852,
       "name": "OpenSesame",
       "html_url": "https://github.com/OpenSesameManager/OpenSesame",
       "languages_url": "https://api.github.com/repos/OpenSesameManager/OpenSesame/languages",
       "language": "Swift"
     },...
 ```
 ### Endpoint
 
 In properties file you will find 
 
 `github.base.url = https://api.github.com/search/repositories?q=created:>{date}&sort=stars&order=desc&page=1&per_page=100`
 
 one request to Github API to retrieve 1 page with 100 repos 
 , `{date}` well be calculated as `today - 30 days`
 
 ### API
 
 ``` 
    @Bean
    public WebClient.Builder webClient();
 ```
 Create webClient.builder to send http requests (reactive env)
 
 ```
    public Flux<Response> callGithubApi(String date);
 ```
 Build and send requests to the endpoint, in connector class.
 
 ``` 
    Flux<Response> getTrendingRepos(); 
 ```
 Call `callGithubApi()` function to get flux of response
 
 ```
    Map<String, Response> getTrendingLanguages();
 ```
 Responsible for getting languages used in trending repos, so it first calls `getTrendingRepos()` function and then process
 the output and returns `Map<String, Response>` -> `Map<language_name, {count= number of repos, items = [repos]}`
 
 ```
    Map<String, Response> prepTLResponse(@NonNull Response trendingRepos)();
 ```
 Build and generate trending languages response as hashtable object
 
 `trendingRepos` must be not null
 
 ```
(Response)trendingRepos = {
   count = number of repos (100)
   items = [
     (Repository){
       id: reposId
       name: repoName
       html_url: repoUrl
       languages_url: url to all languages used in the repo
       language: most used language in the repo
     },...
   ]
}
 ```
 
 If `language` is null, then this repo will be skipped in processing.
 
 ### Extra
 In the next versions of this API, I should implement

 1.  Make it more reactive & parallel
 2.  Consider all languages used in the repo

### License
MIT

