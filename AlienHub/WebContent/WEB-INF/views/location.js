
import * as firebase from 'firebase/app';
import 'firebase/firestore';

// If you're using ES6+/imports/ESM syntax for imports you can do this:
import { GeoCollectionReference, GeoFirestore, GeoQuery, GeoQuerySnapshot } from 'geofirestore';



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
const firestore = firebase.firestore();

// Create a GeoFirestore reference
const geofirestore: GeoFirestore = new GeoFirestore(firestore);

// Create a GeoCollection reference
const geocollection: GeoCollectionReference = geofirestore.collection('restaurants');

// Add a GeoDocument to a GeoCollection
geocollection.add({
  name: 'Geofirestore',
  score: 100,
  // The coordinates field must be a GeoPoint!
  coordinates: new firebase.firestore.GeoPoint(40.7589, -73.9851)
});

// Create a GeoQuery based on a location
const query: GeoQuery = geocollection.near({ center: new firebase.firestore.GeoPoint(40.7589, -73.9851), radius: 1000 });

//
//query.get().then((value GeoQuerySnapshot)) => {
  // All GeoDocument returned by GeoQuery, like the GeoDocument added above
  //console.log(value.docs);
//});