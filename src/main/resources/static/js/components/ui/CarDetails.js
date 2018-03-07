const CarDetails = ({car}) =>
    <section class="container">
        <h2>{car.adName}</h2>

        <ul>
            {car.price == null ? null : <li>Price : {car.price}</li> }
            {car.location == null ? null : <li>Location : {car.location}</li> }
            {car.yearMakeModel == null ? null : <li>Year-make-model : {car.yearMakeModel}</li> }
            {car.odometer == null ? null : <li>Odometer : {car.odometer}</li> }
            {car.condition == null ? null : <li>Condition : {car.condition}</li> }
            {car.cylinders == null ? null : <li>Cylinders : {car.cylinders}</li> }
            {car.drive == null ? null : <li>Drive : {car.drive}</li> }
            {car.fuel == null ? null : <li>Fuel : {car.fuel}</li> }
            {car.paintColor == null ? null : <li>Paint color : {car.paintColor}</li> }
            {car.size == null ? null : <li>Size : {car.size}</li> }
            {car.titleStatus == null ? null : <li>Title status : {car.titleStatus}</li> }
            {car.transmission == null ? null : <li>Transmission : {car.transmission}</li> }
            {car.type == null ? null : <li>Type : {car.type}</li> }
            {car.vin == null ? null : <li>Vin : {car.vin}</li> }
        </ul>

        <p>{car.postBody}</p>
    </section>

export default CarDetails