# bluetooth-bond-helper

Un plugin auxiliar para manejar eventos de emparejamiento Bluetooth (bonding) en Android.

## Install

```bash
npm install bluetooth-bond-helper
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`startBonding(...)`](#startbonding)
* [`isBonded(...)`](#isbonded)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### startBonding(...)

```typescript
startBonding(options: { deviceId: string; }) => Promise<{ success: boolean | null; }>
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ deviceId: string; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean | null; }&gt;</code>

--------------------


### isBonded(...)

```typescript
isBonded(options: { deviceId: string; }) => Promise<{ isBonded: boolean; }>
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ deviceId: string; }</code> |

**Returns:** <code>Promise&lt;{ isBonded: boolean; }&gt;</code>

--------------------

</docgen-api>
