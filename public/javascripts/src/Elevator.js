import React, {useEffect, useState} from 'react'

import ElevatorAPI from './Api'
import './css/Elevator.css'

export default function Elevator(props) {

	const [floorNumber, setFloorNumber] = useState(0);

	useEffect(() => {
		ElevatorAPI.watchElevator('E'+props.elevatorID, (status) => {
			console.log('watcheupdate', status);
			setFloorNumber(status.currentFloor);
		}).then().catch(err => {
			console.error('watchElevator: ', err);
		});
	}, [props.elevatorID]);

	return (
		<div className="Elevator">
			<h4>E{props.elevatorID}</h4>
			<h6>Floor {floorNumber}</h6>
		</div>
	)
}