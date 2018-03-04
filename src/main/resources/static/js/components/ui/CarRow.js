const CarRow = ({car}) =>
    <tr>
        <td>{car.adName}</td>
        <td>{car.price}</td>
        <td>{car.location}</td>
        <td>{car.odometer}</td>
        <td>{car.condition}</td>
        <td>
            <button className="btn btn-success">Details</button>
        </td>

    </tr>

export default CarRow