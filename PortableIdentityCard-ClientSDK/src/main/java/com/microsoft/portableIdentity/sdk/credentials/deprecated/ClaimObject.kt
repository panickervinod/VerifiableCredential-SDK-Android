package com.microsoft.portableIdentity.sdk.credentials.deprecated

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.microsoft.portableIdentity.sdk.crypto.CryptoOperations
import com.microsoft.portableIdentity.sdk.resolvers.IResolver
import com.microsoft.portableIdentity.sdk.utilities.Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ClaimObject(var claimClass: String,
                       @SerialName("@context")
                       var context: String,
                       @SerialName("@type")
                       var type: String,
                       var claimIssuer: String,
                       @Ignore val claimDescriptions: List<ClaimDescription>,
                       @Ignore val claimDetails: ClaimDetail) {
    companion object {
        fun deserialize(claimObject: String): ClaimObject {
            return Serializer.parse(serializer(), claimObject)
        }
    }

    // Room band aid because @Ignore doesn't work in the constructor
    constructor(claimClass: String, context: String, type: String, claimIssuer: String) : this(claimClass, context, type, claimIssuer, emptyList(), SignedClaimDetail(""))

    @PrimaryKey(autoGenerate = true) var uid:Int = 0

    fun serialize(): String {
        return Serializer.stringify(serializer(), this)
    }

    suspend fun getClaimClass(): ClaimClass {
        return ClaimClass.resolve(claimClass)
    }

    suspend fun verify(cryptoOperations: CryptoOperations, resolver: IResolver) {
        claimDetails.verify(cryptoOperations, resolver)
    }
}