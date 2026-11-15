
# Uber Fare Prediction using Linear Regression and Random Forest

# Step 1: Import required libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_absolute_error, mean_squared_error, r2_score


# Step 2: Load the dataset
df = pd.read_csv("uber.csv")

print("First five records:")
print(df.head())

print("\nDataset shape:", df.shape)


# Step 3: Data preprocessing
df = df.drop(columns=["Unnamed: 0", "key"], errors="ignore")

df = df.dropna()
df = df.drop_duplicates()

# Convert pickup_datetime into datetime format
df["pickup_datetime"] = pd.to_datetime(
    df["pickup_datetime"], errors="coerce", utc=True
)

df = df.dropna(subset=["pickup_datetime"])

# Extract date and time features
df["hour"] = df["pickup_datetime"].dt.hour
df["day"] = df["pickup_datetime"].dt.day
df["month"] = df["pickup_datetime"].dt.month
df["weekday"] = df["pickup_datetime"].dt.weekday


# Step 4: Calculate distance using Haversine formula
def haversine(lat1, lon1, lat2, lon2):
    lat1, lon1, lat2, lon2 = map(
        np.radians, [lat1, lon1, lat2, lon2]
    )

    dlat = lat2 - lat1
    dlon = lon2 - lon1

    a = (
        np.sin(dlat / 2) ** 2
        + np.cos(lat1) * np.cos(lat2) * np.sin(dlon / 2) ** 2
    )

    return 6371 * 2 * np.arcsin(np.sqrt(a))


df["distance_km"] = haversine(
    df["pickup_latitude"],
    df["pickup_longitude"],
    df["dropoff_latitude"],
    df["dropoff_longitude"]
)


# Step 5: Remove invalid records
df = df[
    (df["fare_amount"] > 0) &
    (df["passenger_count"] > 0) &
    (df["passenger_count"] <= 6) &
    (df["pickup_latitude"].between(-90, 90)) &
    (df["dropoff_latitude"].between(-90, 90)) &
    (df["pickup_longitude"].between(-180, 180)) &
    (df["dropoff_longitude"].between(-180, 180)) &
    (df["distance_km"] > 0)
]


# Step 6: Remove outliers using IQR
for col in ["fare_amount", "distance_km"]:
    Q1 = df[col].quantile(0.25)
    Q3 = df[col].quantile(0.75)

    IQR = Q3 - Q1

    lower = Q1 - 1.5 * IQR
    upper = Q3 + 1.5 * IQR

    df = df[
        (df[col] >= lower) &
        (df[col] <= upper)
    ]

print("\nCleaned dataset shape:", df.shape)


# Step 7: Select input features and target
features = [
    "pickup_longitude",
    "pickup_latitude",
    "dropoff_longitude",
    "dropoff_latitude",
    "passenger_count",
    "distance_km",
    "hour",
    "day",
    "month",
    "weekday"
]

X = df[features]
y = df["fare_amount"]


# Step 8: Split dataset into training and testing data
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)


# Step 9: Train Linear Regression model
lr = LinearRegression()
lr.fit(X_train, y_train)

lr_pred = lr.predict(X_test)


# Step 10: Train Random Forest model
rf = RandomForestRegressor(
    n_estimators=100,
    max_depth=15,
    random_state=42,
    n_jobs=-1
)

rf.fit(X_train, y_train)

rf_pred = rf.predict(X_test)


# Step 11: Evaluate both models
def evaluate_model(name, actual, predicted):
    print("\n", name)
    print("R2 Score:", round(r2_score(actual, predicted), 4))
    print("MAE:", round(mean_absolute_error(actual, predicted), 4))
    print("RMSE:", round(np.sqrt(mean_squared_error(actual, predicted)), 4))


evaluate_model("Linear Regression", y_test, lr_pred)
evaluate_model("Random Forest", y_test, rf_pred)


# Step 12: Compare model performance
print("\nModel Comparison")
print("Linear Regression R2:", r2_score(y_test, lr_pred))
print("Random Forest R2:", r2_score(y_test, rf_pred))


# Step 13: Predict fare for a new ride
pickup_lat = float(input("\nEnter pickup latitude: "))
pickup_lon = float(input("Enter pickup longitude: "))

drop_lat = float(input("Enter drop-off latitude: "))
drop_lon = float(input("Enter drop-off longitude: "))

passengers = int(input("Enter number of passengers: "))

# Calculate distance
distance = haversine(
    pickup_lat, pickup_lon, drop_lat, drop_lon
)

# Use example date and time features
new_ride = pd.DataFrame([{
    "pickup_longitude": pickup_lon,
    "pickup_latitude": pickup_lat,
    "dropoff_longitude": drop_lon,
    "dropoff_latitude": drop_lat,
    "passenger_count": passengers,
    "distance_km": distance,
    "hour": 18,
    "day": 15,
    "month": 6,
    "weekday": 2
}])

predicted_fare = rf.predict(new_ride)

print("\nDistance:", round(distance, 2), "km")
print("Predicted Uber Fare: $", round(predicted_fare[0], 2))
