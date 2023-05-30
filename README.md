# word-counter

The WordCounterImpl class implements the WordCounter interface and provides the functionality to count the occurrences of words.

### Methods:
  - addWords method: This method takes one or more words as input and adds them to the word counter. It iterates over each word and performs the following steps: 
      - Checks if the word is valid using the isValidWord method. The isValidWord method uses a regular expression ([a-zA-Z]+) to ensure that the word consists only of alphabetic characters.
      - If the word is valid, it translates the word to its English equivalent using the Translator.translate method.
      - It then updates the count of the translated word in the wordCounts map using the compute method. The compute method takes a lambda function that increments the count of the word if it already exists in the map or initializes it with a count of 1 if it doesn't exist.
  - countWord method: This method takes a word as input and returns the count of occurrences for that word. It follows the same steps as the addWords method to translate the word and retrieve the count from the wordCounts map. If the word doesn't exist in the map, it returns 0.
  - isValidWord method: This method takes a word as input and uses a regular expression to check if it consists only of alphabetic characters. It returns true if the word is valid and false otherwise.
  - ConcurrentMap<String, Integer> wordCounts: This is a concurrent map that stores the count of each word. The map is defined using the ConcurrentHashMap class, which allows concurrent access and updates.

The WordCounterImpl class provides a thread-safe implementation of the WordCounter interface using the ConcurrentHashMap and ensures that words are counted accurately even in concurrent environments.

      - IWordCounter: Library for external clients
      - IWordValidator and WordValidator : Validation of words (Separation of concerns)
      - Translator : External Util

 - The library and REST layer is separated for loose coupling,The REST Microservice is independent of the logic in word-counter.

 - The library is deployed to maven repo(can be hosted in nexus repo's)(https://s01.oss.sonatype.org/#nexus-search;quick~word-counter)


Swagger UI (http://localhost:8080/swagger-ui/index.html)








