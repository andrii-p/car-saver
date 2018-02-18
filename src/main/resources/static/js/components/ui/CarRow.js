const CarRow = ({car}) =>
    <tr>
        <td>{car.adName}</td>
        <td>{car.price}</td>
        <td>{car.location}</td>
        <td>{car.odometer}</td>
        <td>{car.condition}</td>
    </tr>

export default CarRow