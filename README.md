# pact-demo
Pact Java Example

* Consumer
  * Import the consumer project to Idea
  * Run `gradle test`
  * This will generate pact json in the target/pacts folder
* Broker
  * Now that you have the pact json, we need to publish it to the broker so that the provider can access it
  * Install postgres
  * Run `docker-compose up` in the broker folder
  * Make sure broker is running and then run `gradle pactPublish` inside consumer project
  * Now you should be able to see the pact file published on the broker UI
* Provider
  * Go to provider project
  * Run the SpringBoot application
  * Run `gradle pactVerify`
  * The pact broker will now show verified
