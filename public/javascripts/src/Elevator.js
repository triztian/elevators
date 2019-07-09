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

	return (
		<div className="Elevator-Line">
			{ [...Array(floorNumber).keys()].map(nth => 
				<div className="Elevator-Stub" key={nth}></div>
			)
			}
			<div className="Elevator">
				<h4>E{props.elevatorID}</h4>
				<h6>Floor {floorNumber}</h6>
			</div>
		</div>
	)
}