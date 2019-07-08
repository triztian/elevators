import React, {useEffect, useState} from 'react';
import './App.css';

import ElevatorAPI from './ElevatorAPI/Api'

import Floor from './Floor'

export default function App() {
	
	const [floors, setFloors] = useState([]);
	
	useEffect(() => {

		ElevatorAPI.listFloors()
		.then(setFloors)
		.catch(err => {
			console.error('listFloors: ', err)
		});

	}, [floors]);
	
	return (
		<div className="App">
			<h1 className="App-Header">Elevator</h1>
			<div className="Floors">{floors.reverse().map(floor => {
				return <Floor key={`f${floor.number}`} floors={floors} floorName={floor.name} floorNumber={floor.number} />
			})}</div>
		</div>
	);
}