import React, {useEffect, useState} from 'react'

import ElevatorAPI from './Api'
import './css/Elevator.css'

/**
 * 
 * @param {*} props 
 */
export default function Elevator(props) {

	const [floorNumber, setFloorNumber] = useState(0);

	useEffect(() => {
		ElevatorAPI.getElevator(props.elevatorID).then(elev => {
			setFloorNumber(elev.currentFloor.number);
		}).catch(err => {
			console.error('getElevator: ', err);
		});

	}, [props.elevatorID]);

	useEffect(() => {
		ElevatorAPI.watchElevator('E'+props.elevatorID, (status) => {
			console.log('watchElevator: ', status);
			setFloorNumber(status.currentFloor.number);
		}).then((elevatorID) => {
			console.log('watching elevator: ', elevatorID);
		}).catch(err => {
			console.error('watchElevator: ', err);
		});
	}, [props.elevatorID]);

	let slots = props.floors.map(f => {
		if (floorNumber === f.number) {
			return (
				<div id={`EF${f.number}`} className={props.debug ? 'Elevator debug': 'Elevator'} key={f.number}>
					<h4>E{props.elevatorID}</h4>
					<h6>Floor {floorNumber}</h6>
				</div>
			);
		}
		return <div id={`EF${f.number}`} className={props.debug ? 'Elevator-Stub debug':'Elevator-Stub'} key={f.number}></div>
	});

	return (
		<div className={props.debug ? "Elevator-Line debug" : "Elevator-Line"}>
			{slots}
		</div>
	)
}