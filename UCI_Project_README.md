# Milestone II Changes

All edits for Milestone II can be found within the `XMLTest.java` and `XML.java` files. We introduced two new overloaded parse methods, in addition to the two methods we were required to implement. These implementations are used to facilitate both of the required methods. Testing methods for these changes are located at the bottom of the `XMLTest.java` file.

# Milestone III Changes

For Milestone III, modifications were made within the `XMLTest.java` and `XML.java` files. All tests related to these changes are located at the bottom of the XML testing file. Within `XML.java`, we added two new methods: an overloaded parse method that accepts a function parameter and the `toJSONObject` method, which utilizes the new parse method for its operation and takes in a function parameter.

# Milestone III Discussion

In this phase, we introduced an additional parse method that accepts a Java `Function` object. This function is designed to take in a key as an object and return a modified key as an object. It integrates seamlessly with the parse method, allowing for real-time modification of keys during the XML to JSON conversion process. This functionality enables the dynamic alteration of keys to reflect the desired changes specified by the input function parameter.

# Increased Efficiency with the Changes

The recent enhancements to the parse method offer significant efficiency improvements over the initial Milestone I implementation, for several reasons:

- **Milestone I Implementation:**
  - Converted the entire XML object into a JSON string.
  - Employed a recursive method to locate each object key and nested object key, replacing the value with a prefix.

- **New Implementation:**
  - Directly replaces each key encountered during the JSON conversion process with a new key, applying the provided function to modify the key.
