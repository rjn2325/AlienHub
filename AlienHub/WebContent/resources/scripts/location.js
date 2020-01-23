//import * as firebase from 'firebase/app.js';
//import 'firebase/firestore.js';
//import { GeoCollectionReference, GeoFirestore, GeoQuery, GeoQuerySnapshot } from 'geofirestore.js';
//


// Initialize the Firebase SDK
firebase.initializeApp({
	apiKey: "GcPVh9kkUSbi2pP7CGRif83mbjwWkdmcCnJzBpM",
	  authDomain: "alienhub-4c22a.firebaseapp.com",
	  databaseURL: "https://alienhub-4c22a.firebaseio.com",
	  projectId: "alienhub-4c22a",
	  storageBucket: "",
	  messagingSenderId: "920881843329",
	  appId: "1:920881843329:web:e3f50734a72370d5"

});

// Create a Firestore reference
var firestore = firebase.firestore();

// Create a GeoFirestore reference
var geofirestore; GeoFirestore = new GeoFirestore(firestore);

// Create a GeoCollection reference
var geocollection;GeoCollectionReference = geofirestore.collection('restaurants');

// Add a GeoDocument to a GeoCollection
geocollection.add({
  name: 'Geofirestore',
  score: 100,
  // The coordinates field must be a GeoPoint!
  coordinates: new firebase.firestore.GeoPoint(40.7589, -73.9851)
});

// Create a GeoQuery based on a location
var query;GeoQuery = geocollection.near({ center: new firebase.firestore.GeoPoint(40.7589, -73.9851), radius: 1000 });

//
//query.get().then((value GeoQuerySnapshot)) => {
  // All GeoDocument returned by GeoQuery, like the GeoDocument added above
  //console.log(value.docs);
//});