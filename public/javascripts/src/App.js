import React, {useEffect, useState} from 'react';
import './css/App.css';

import ElevatorAPI from './Api'

import Floor from './Floor'
import Elevator from './Elevator'

export default function App() {
	
	const [floors, setFloors] = useState([]);
	
	useEffect(() => {

		ElevatorAPI.listFloors()
			.then(setFloors)
			.catch(err => {
				console.error('listFloors: ', err)
			});
	}, []);
	
	return (
		<div className="App">
			<h1 className="App-Header">Elevator</h1>
			<div className="Building">
				<div className="Floors">{floors.reverse().map(floor => 
					<Floor key={`f${floor.number}`} floors={floors} floorName={floor.name} floorNumber={floor.number} />
				)}</div>
				<div className="Elevators">
					{[...Array(3).keys()].map(n =>
							<Elevator key={'E'+n} elevatorID={n} />
					)}
				</div>
			</div>
		</div>
	);
}