package com.demoapp.development.feature.medications.domain.mapper

import com.demoapp.development.common.dispatcher.DefaultDispatcher
import com.demoapp.development.feature.medications.data.local.MedicationEntity
import com.demoapp.development.feature.medications.domain.model.Medication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class MedicationMapper @Inject constructor(@DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher) {

    suspend fun mapRemoteMedicationListToDomain(medicationResponse: String?): List<MedicationEntity> {
        return withContext(defaultDispatcher) {
            val items = arrayListOf<MedicationEntity>()
            try {
                val jsonObject = JSONObject(medicationResponse);
                val problemsArray = jsonObject.getJSONArray("problems")
                for (i in 0 until problemsArray.length()) {
                    val problemsObject = problemsArray.getJSONObject(i)
                    val keysIterator = problemsObject.keys()
                    while (keysIterator.hasNext()) {
                        val key = keysIterator.next()
                        val problemsArray = problemsObject.optJSONArray(key)
                        if (problemsArray != null && problemsArray.length() > 0) {
                            for (j in 0 until problemsArray.length()) {
                                val problemObject = problemsArray.getJSONObject(j)

                                // Parse dynamic key (Diabetes, Asthma, etc.)
                                Timber.d("Problem Type: $key")

                                // Parse medications
                                val medicationsArray = problemObject.optJSONArray("medications")
                                if (medicationsArray != null && medicationsArray.length() > 0) {
                                    val medicationsObject = medicationsArray.getJSONObject(0)

                                    // Parse medicationsClasses
                                    val medicationsClassesArray =
                                        medicationsObject.optJSONArray("medicationsClasses")

                                    for (k in 0 until medicationsClassesArray.length()) {
                                        val medicationObject =
                                            medicationsClassesArray.getJSONObject(k);
                                        val medicationKeyIterator = medicationObject.keys()

                                        while (medicationKeyIterator.hasNext()) {
                                            val medicationKey = medicationKeyIterator.next()
                                            val classArray =
                                                medicationObject.optJSONArray(medicationKey);
                                            for (l in 0 until classArray.length()) {
                                                Timber.d("class Type: $medicationKey")
                                                val classObject = classArray.getJSONObject(l)

                                                val drugsIterator = classObject.keys()
                                                while (drugsIterator.hasNext()) {
                                                    val drugsKey = drugsIterator.next()
                                                    Timber.d("the drugs key is:" + drugsKey)

                                                    val associatedDrugArray =
                                                        classObject.optJSONArray(drugsKey)
                                                    if (associatedDrugArray != null && associatedDrugArray.length() > 0) {
                                                        for (m in 0 until associatedDrugArray.length()) {
                                                            val associatedDrugObject =
                                                                associatedDrugArray.getJSONObject(m)

                                                            // Extract values
                                                            val name =
                                                                associatedDrugObject.getString("name")
                                                            val dose =
                                                                associatedDrugObject.getString("dose")
                                                            val strength =
                                                                associatedDrugObject.getString("strength")

                                                            // Use the extracted values as needed
                                                            Timber.d("Key: $key, Name: $name, Dose: $dose, Strength: $strength")

                                                            items.add(
                                                                MedicationEntity(

                                                                    name = name,
                                                                    dose = dose,
                                                                    strength = strength,
                                                                    problem = key
                                                                )
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            items;
        }
        // }
    }

    suspend fun mapDomainsToUi(domainEntity: List<MedicationEntity>): List<Medication> {
        return withContext(defaultDispatcher) {
            domainEntity.map {
                Medication(
                    id = it.id, name = it.name, dose = it.dose, strength = it.strength
                )
            }
        }
    }

    suspend fun mapDomainToUi(domainEntity: MedicationEntity): Medication {
        return withContext(defaultDispatcher) {
            Medication(
                id = domainEntity.id,
                name = domainEntity.name,
                dose = domainEntity.dose,
                strength = domainEntity.strength
            )
        }
    }

}