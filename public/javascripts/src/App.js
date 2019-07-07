import React, {useEffect, useState} from 'react';
import './App.css';

import Floor from './Floor'

import ElevatorAPI from './ElevatorAPI/Api'

export default function App() {

  let api = new ElevatorAPI();

  const [floors, setFloors] = useState([]);

  useEffect(() => {
	api.listFloors().then((f) => {
		setFloors(f.map((floor) => {
			return <Floor floors={f} floorName={floor.name} floorNumber={floor.number} />
		}));
	}).catch(err => {
		console.log(err)
	});
  });

  return (
    <div className="App">
	  <div className="floors">{floors}</div>
    </div>
  );
}
