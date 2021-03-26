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

4. To run the tests:

        cd opening-hours
        sbt test