import React, {useEffect, useState} from 'react';
import './css/App.css';

import ElevatorAPI from './Api'

import Floor from './Floor'
import Elevator from './Elevator'

export default function App() {
	
	const [floors, setFloors] = useState([]);

	const debug = false;
	
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
					<Floor key={`f${floor.number}`} floors={floors} floorName={floor.name} floorNumber={floor.number} debug={debug}/>
				)}</div>
				<div className={debug ? 'Elevators debug': 'Elevators'}>
					{/* 3 elevator lines */}
					{[...Array(3).keys()].map(n =>
							<Elevator key={'E'+n} elevatorID={n} debug={debug} floors={floors}/>
					)}
				</div>
			</div>
		</div>
	);
}