import React, {useEffect, useState} from 'react';
import './App.css';

import ElevatorAPI from './ElevatorAPI/Api'

import Floor from './Floor'

export default function App() {
	
	const [floors, setFloors] = useState([]);
	
	useEffect(() => {
		ElevatorAPI.listFloors().then((f) => {
			setFloors(f.map((floor) => {
				return <Floor key={`f${floor.number}`} floors={f} floorName={floor.name} floorNumber={floor.number} />
			}));
		}).catch(err => {
			console.error('listFlors: ', err)
		});
	});
	
	return (
		<div className="App">
		<div className="floors">{floors}</div>
		</div>
		);
}