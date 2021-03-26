# opening-hours
A simple program that takes JSON-formatted opening hours of a restaurant an input and outputs hours in more human readable format.

### Usage
You need to download and install sbt for this application to run.

1. Download the template:

        git clone git@github.com:arametta/opening-hours.git

2. Launch sbt:

        cd opening-hours
        sbt run

3. In a new terminal window use the curl command to pass the json file openingHours.json:

        cd opening-hours
        curl -X POST -H "Content-Type: application/json" -d @openingHours.json localhost:9000/readJson

4. To test the map implementation use

        curl -X POST -H "Content-Type: application/json" -d @openingHours.json localhost:9000/readJsonMap

5. To run the tests:

        cd opening-hours
        sbt test

Instead of the structure as <days-of-the-week>:<opening-hours> I would've defined the JSON like this:

      [
         {
            "day" : "monday",
            "opening-hours": <opening-hours>
         },
         {  "day" : "tuesday",
            "opening-hours": <opening-hours>
         },
         ...
      ]

This way it could've been stored in a list of

      case class Days(day: String, opening: List[Opening])

I think that iterate over it could've required less code.